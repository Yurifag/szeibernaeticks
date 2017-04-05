#!/usr/bin/env python3
'''
Args:
    param1: BUILD SCRIPT LOCATION
    param2: MOD_ID
    param3: NAMESPACE.MOD_ID

Script to automatically create placeholder .json and .png files for items and blocks (and ItemBlocks).
Blocks must be listed at the beginning of the ModBlocks class.
Items must be listed at the beginning of the ModItems class.
This script gets called by build.gradle.
'''
import os, sys, re, json

working_dir = sys.argv[1]
mod_id = sys.argv[2]
group = sys.argv[3].replace(".", "/")
src_path = working_dir + "/src/main/" + group + "/" 
assets_path = working_dir + "/src/main/resources/assets/" + mod_id + "/"

with open(working_dir + "/util/placeholder.png", "rb") as file:
  placeholder = file.read()

item_model = {
  "parent": "item/generated",
  "textures": {
    "layer0": ""
  }
}

itemblock_model = {
  "parent": ""
}

block_model = {
  "parent": "block/cube_all",
  "textures": {
    "all": ""
  }
}

block_state = {
  "variants": {
    "normal": {
      "model": ""
    }
  }
}

def read_file(path):
  with open(path, "r") as file:
    return file.read()

def write_file(path, data):
  with open(path, "w+") as file:
    file.write(data)

def create_json_file(path, data):
  write_file(path, json.dumps(data, sort_keys=True, indent=2) + "\n")

def create_png(path, image):
  with open(path, "w+b") as file:
    file.write(image)

def create_item_files(items, item_type):
  for item in items:
    model_path = assets_path + "models/item/" + item + ".json"
    item_textures_path = assets_path + "textures/items/" + item + ".png"
    if not os.path.exists(model_path):
      if item_type == "item":
        item_model["textures"]["layer0"] = mod_id + ":items/" + item
        create_json_file(model_path, item_model)
      elif item_type == "block":
        itemblock_model["parent"] = mod_id + ":block/" + item
        create_json_file(model_path, itemblock_model)
    if not os.path.exists(item_textures_path):
      create_png(item_textures_path, placeholder)

def create_block_files(blocks):
  for block in blocks:
    model_path = assets_path + "models/block/" + block + ".json"
    blockstates_path = assets_path + "blockstates/" + block + ".json"
    block_textures_path = assets_path + "textures/blocks/" + block + ".png"

    if not os.path.exists(model_path):
      block_model["textures"]["all"] =  mod_id + ":blocks/" + block
      create_json_file(model_path, block_model)

    if not os.path.exists(blockstates_path):
      block_state["variants"]["normal"]["model"] = mod_id + ":" + block
      create_json_file(blockstates_path, block_state)

    if not os.path.exists(block_textures_path):
      create_png(block_textures_path, placeholder)

def create_lang_file(items, blocks):
  items = ["item." + item + ".name" for item in items] + ["tile." + block + ".name" for block in blocks]
  lines = []
  for line in read_file(assets_path + "lang/en_US.lang").splitlines():
    line.strip()
    if not line == "" and "#" not in line:
      lines.append(line)

  config = []
  for line in lines:
    entry = line.split("=")
    config.append(entry)
    for item in items:
      if item == entry[0]:
        items.remove(item)
  config += [[item, "Placeholder"] for item in items]
  config.sort()
  config_string = ""
  for pair in config:
    config_string += pair[0] + "=" + pair[1] + "\n"
  write_file(assets_path + "lang/en_US.lang", config_string)

def main():
  for directory in ["blockstates", "lang", "models/block", "models/item", "textures/blocks", "textures/items"]:
    os.makedirs(assets_path + directory, exist_ok=True)

  if not os.path.exists(assets_path + "lang/en_US.lang"):
    write_file(assets_path + "lang/en_US.lang", "")

  regex = re.compile("public final class .* {\n[\s\S]*? {4}\n")

  # get block and item names from the class files (yes, I hate myself for this as much as you do)
  items = re.findall("public static Item.* (.*);", regex.search(read_file(src_path + "item/ModItems.java")).group(0))
  blocks = re.findall("public static Block.* (.*);", regex.search(read_file(src_path + "block/ModBlocks.java")).group(0))

  create_item_files(items, "item")
  create_item_files(blocks, "block")
  create_block_files(blocks)
  create_lang_file(items, blocks)

if __name__ == "__main__":
  main()
