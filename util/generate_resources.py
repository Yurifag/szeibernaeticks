#!/usr/bin/env python2
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
import os, sys, re, json, gimp
from gimpenums import *

working_dir = sys.argv[0]
mod_id = sys.argv[1]
group = sys.argv[2].replace(".", "/")

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

def create_gui_textures(guis):
  IMAGE_SIZE                     = 256
  ITEM_SLOT_SIZE = HOTBAR_HEIGHT = 18
  ITEM_SLOT_BORDER_SIZE          = 1
  MAIN_INVENTORY_HEIGHT          = 3 * ITEM_SLOT_SIZE
  HOTBAR_MARGIN_TOP              = 4
  GUI_BORDER_SIZE                = 7
  MARGIN_BORDER_TOP              = 10
  GUI_WIDTH                      = 176

  COLORS = [
    [0,   0,   0,   255],
    [55,  55,  55,  255],
    [85,  85,  85,  255],
    [139, 139, 139, 255],
    [198, 198, 198, 255],
    [255, 255, 255, 255]
  ]

  for gui_name, GUI_CUSTOM_SECTION_HEIGHT in guis.iteritems():
    xcf_path = working_dir + "/util/gui-xcf/" + gui_name + ".xcf"
    png_path = assets_path + "/textures/gui/container/" + gui_name + ".png"
    if not (os.path.exists(xcf_path) or os.path.exists(png_path)):
      GUI_HEIGHT = 2 * GUI_BORDER_SIZE + MARGIN_BORDER_TOP + GUI_CUSTOM_SECTION_HEIGHT + MAIN_INVENTORY_HEIGHT + HOTBAR_MARGIN_TOP + HOTBAR_HEIGHT

      image = gimp.Image(IMAGE_SIZE, IMAGE_SIZE, RGB)
      image.disable_undo()
      layers = {
        "background":        gimp.Layer(image, "background",        IMAGE_SIZE,                      IMAGE_SIZE,                       RGB_IMAGE, 100, NORMAL_MODE),
        "edge-top-left":     gimp.Layer(image, "top left edge",     GUI_BORDER_SIZE,                 GUI_BORDER_SIZE,                  RGB_IMAGE, 100, NORMAL_MODE),
        "edge-top-right":    gimp.Layer(image, "top right edge",    GUI_BORDER_SIZE,                 GUI_BORDER_SIZE,                  RGB_IMAGE, 100, NORMAL_MODE),
        "edge-bottom-left":  gimp.Layer(image, "bottom left edge",  GUI_BORDER_SIZE,                 GUI_BORDER_SIZE,                  RGB_IMAGE, 100, NORMAL_MODE),
        "edge-bottom-right": gimp.Layer(image, "bottom right edge", GUI_BORDER_SIZE,                 GUI_BORDER_SIZE,                  RGB_IMAGE, 100, NORMAL_MODE),
        "border-top":        gimp.Layer(image, "border top",        GUI_WIDTH - 2 * GUI_BORDER_SIZE, GUI_BORDER_SIZE,                  RGB_IMAGE, 100, NORMAL_MODE),
        "border-left":       gimp.Layer(image, "border left",       GUI_BORDER_SIZE,                 GUI_HEIGHT - 2 * GUI_BORDER_SIZE, RGB_IMAGE, 100, NORMAL_MODE),
        "border-bottom":     gimp.Layer(image, "border bottom",     GUI_WIDTH - 2 * GUI_BORDER_SIZE, GUI_BORDER_SIZE,                  RGB_IMAGE, 100, NORMAL_MODE),
        "border-right":      gimp.Layer(image, "border right",      GUI_BORDER_SIZE,                 GUI_HEIGHT - 2 * GUI_BORDER_SIZE, RGB_IMAGE, 100, NORMAL_MODE),
        "margin-border-top": gimp.Layer(image, "margin border top", GUI_WIDTH - 2 * GUI_BORDER_SIZE, MARGIN_BORDER_TOP,                RGB_IMAGE, 100, NORMAL_MODE),
        "inner-gui":         gimp.Layer(image, "inner GUI",         GUI_WIDTH - 2 * GUI_BORDER_SIZE, GUI_CUSTOM_SECTION_HEIGHT,        RGB_IMAGE, 100, NORMAL_MODE),
        "inventory":         gimp.Layer(image, "inventory",         GUI_WIDTH - 2 * GUI_BORDER_SIZE, MAIN_INVENTORY_HEIGHT,            RGB_IMAGE, 100, NORMAL_MODE),
        "margin-hotbar-top": gimp.Layer(image, "margin hotbar top", GUI_WIDTH - 2 * GUI_BORDER_SIZE, HOTBAR_MARGIN_TOP,                RGB_IMAGE, 100, NORMAL_MODE),
        "hotbar":            gimp.Layer(image, "hotbar",            GUI_WIDTH - 2 * GUI_BORDER_SIZE, HOTBAR_HEIGHT,                    RGB_IMAGE, 100, NORMAL_MODE)
      }
      for layer in layers.itervalues():
        gimp.pdb["gimp-layer-add-alpha"](layer)
        gimp.pdb["gimp-image-insert-layer"](image, layer, None, 0)
        drawable = gimp.pdb["gimp-image-active-drawable"](image)
        gimp.pdb["gimp-selection-all"](image)
        gimp.pdb["gimp-edit-cut"](drawable)
        gimp.pdb["gimp-selection-none"](image)

      gimp.pdb["gimp-layer-set-offsets"](layers["edge-top-right"],    GUI_WIDTH - GUI_BORDER_SIZE, 0)
      gimp.pdb["gimp-layer-set-offsets"](layers["edge-bottom-left"],  0,                           GUI_HEIGHT - GUI_BORDER_SIZE)
      gimp.pdb["gimp-layer-set-offsets"](layers["edge-bottom-right"], GUI_WIDTH - GUI_BORDER_SIZE, GUI_HEIGHT - GUI_BORDER_SIZE)
      gimp.pdb["gimp-layer-set-offsets"](layers["margin-border-top"], GUI_BORDER_SIZE,             GUI_BORDER_SIZE)
      gimp.pdb["gimp-layer-set-offsets"](layers["border-top"],        GUI_BORDER_SIZE,             0)
      gimp.pdb["gimp-layer-set-offsets"](layers["border-bottom"],     GUI_BORDER_SIZE,             GUI_HEIGHT - GUI_BORDER_SIZE)
      gimp.pdb["gimp-layer-set-offsets"](layers["border-left"],       0,                           GUI_BORDER_SIZE)
      gimp.pdb["gimp-layer-set-offsets"](layers["border-right"],      GUI_WIDTH - GUI_BORDER_SIZE, GUI_BORDER_SIZE)
      gimp.pdb["gimp-layer-set-offsets"](layers["inner-gui"],         GUI_BORDER_SIZE,             GUI_BORDER_SIZE + MARGIN_BORDER_TOP)
      gimp.pdb["gimp-layer-set-offsets"](layers["inventory"],         GUI_BORDER_SIZE,             GUI_BORDER_SIZE + MARGIN_BORDER_TOP + GUI_CUSTOM_SECTION_HEIGHT)
      gimp.pdb["gimp-layer-set-offsets"](layers["margin-hotbar-top"], GUI_BORDER_SIZE,             GUI_BORDER_SIZE + MARGIN_BORDER_TOP + GUI_CUSTOM_SECTION_HEIGHT + MAIN_INVENTORY_HEIGHT)
      gimp.pdb["gimp-layer-set-offsets"](layers["hotbar"],            GUI_BORDER_SIZE,             GUI_BORDER_SIZE + MARGIN_BORDER_TOP + GUI_CUSTOM_SECTION_HEIGHT + MAIN_INVENTORY_HEIGHT + HOTBAR_MARGIN_TOP)
      
      gimp.set_foreground(tuple(COLORS[4]))
      gimp.pdb["gimp-edit-fill"](layers["margin-border-top"], FOREGROUND_FILL)
      gimp.pdb["gimp-edit-fill"](layers["inner-gui"],         FOREGROUND_FILL)
      gimp.pdb["gimp-edit-fill"](layers["margin-hotbar-top"], FOREGROUND_FILL)

      gimp.pdb["gimp-image-add-vguide"](image, 0)
      gimp.pdb["gimp-image-add-vguide"](image, GUI_BORDER_SIZE)
      gimp.pdb["gimp-image-add-vguide"](image, GUI_WIDTH - GUI_BORDER_SIZE)
      gimp.pdb["gimp-image-add-vguide"](image, GUI_WIDTH)
      gimp.pdb["gimp-image-add-hguide"](image, GUI_BORDER_SIZE)
      gimp.pdb["gimp-image-add-hguide"](image, GUI_BORDER_SIZE + MARGIN_BORDER_TOP)
      gimp.pdb["gimp-image-add-hguide"](image, GUI_BORDER_SIZE + MARGIN_BORDER_TOP + GUI_CUSTOM_SECTION_HEIGHT)
      gimp.pdb["gimp-image-add-hguide"](image, GUI_BORDER_SIZE + MARGIN_BORDER_TOP + GUI_CUSTOM_SECTION_HEIGHT + MAIN_INVENTORY_HEIGHT)
      gimp.pdb["gimp-image-add-hguide"](image, GUI_BORDER_SIZE + MARGIN_BORDER_TOP + GUI_CUSTOM_SECTION_HEIGHT + MAIN_INVENTORY_HEIGHT + HOTBAR_MARGIN_TOP)
      gimp.pdb["gimp-image-add-hguide"](image, GUI_BORDER_SIZE + MARGIN_BORDER_TOP + GUI_CUSTOM_SECTION_HEIGHT + MAIN_INVENTORY_HEIGHT + HOTBAR_MARGIN_TOP + HOTBAR_HEIGHT)
      gimp.pdb["gimp-image-add-hguide"](image, GUI_HEIGHT)

      gimp.set_foreground(tuple(COLORS[3]))
      gimp.pdb["gimp-edit-fill"](layers["hotbar"], FOREGROUND_FILL)
      for i in xrange(0, 17):
        gimp.pdb["gimp-drawable-set-pixel"](layers["hotbar"], i,     0,     4, COLORS[1])
        gimp.pdb["gimp-drawable-set-pixel"](layers["hotbar"], 0,     i,     4, COLORS[1])
        gimp.pdb["gimp-drawable-set-pixel"](layers["hotbar"], i + 1, 17,    4, COLORS[5])
        gimp.pdb["gimp-drawable-set-pixel"](layers["hotbar"], 17,    i + 1, 4, COLORS[5])

      x = GUI_BORDER_SIZE
      y = GUI_BORDER_SIZE + MARGIN_BORDER_TOP + GUI_CUSTOM_SECTION_HEIGHT + MAIN_INVENTORY_HEIGHT + HOTBAR_MARGIN_TOP
      gimp.pdb["gimp-image-set-active-layer"](image, layers["hotbar"])
      gimp.pdb["gimp-image-select-rectangle"](image, CHANNEL_OP_REPLACE, x, y, ITEM_SLOT_SIZE, ITEM_SLOT_SIZE)
      gimp.pdb["gimp-edit-copy"             ](layers["hotbar"])
      for i in xrange(1, 9):
        gimp.pdb["gimp-image-select-rectangle"](image, CHANNEL_OP_REPLACE, x + i * ITEM_SLOT_SIZE, y, ITEM_SLOT_SIZE, ITEM_SLOT_SIZE)
        gimp.pdb["gimp-floating-sel-anchor"](gimp.pdb["gimp-edit-paste"](layers["hotbar"], True))

      gimp.pdb["gimp-image-select-rectangle"](image, CHANNEL_OP_REPLACE, x, y, ITEM_SLOT_SIZE * 9, ITEM_SLOT_SIZE)
      gimp.pdb["gimp-edit-copy"             ](layers["hotbar"])
      gimp.pdb["gimp-image-set-active-layer"](image, layers["inventory"])
      y = GUI_BORDER_SIZE + MARGIN_BORDER_TOP + GUI_CUSTOM_SECTION_HEIGHT
      for i in xrange(0, 3):
        gimp.pdb["gimp-image-select-rectangle"](image, CHANNEL_OP_REPLACE, x, y + i * ITEM_SLOT_SIZE, GUI_WIDTH - 2 * GUI_BORDER_SIZE, ITEM_SLOT_SIZE)
        gimp.pdb["gimp-floating-sel-anchor"](gimp.pdb["gimp-edit-paste"](layers["inventory"], True))

      for x in xrange(0, GUI_WIDTH - 2 * GUI_BORDER_SIZE):
        gimp.pdb["gimp-drawable-set-pixel"](layers["border-top"],    x, 0,                   4, COLORS[0])
        gimp.pdb["gimp-drawable-set-pixel"](layers["border-bottom"], x, GUI_BORDER_SIZE - 1, 4, COLORS[0])
        for y in xrange(1, 3):
          gimp.pdb["gimp-drawable-set-pixel"](layers["border-top"],    x, y,                       4, COLORS[5])
          gimp.pdb["gimp-drawable-set-pixel"](layers["border-bottom"], x, GUI_BORDER_SIZE - 1 - y, 4, COLORS[2])
        for y in xrange(3, 7):
          gimp.pdb["gimp-drawable-set-pixel"](layers["border-top"],    x, y,                       4, COLORS[4])
          gimp.pdb["gimp-drawable-set-pixel"](layers["border-bottom"], x, GUI_BORDER_SIZE - 1 - y, 4, COLORS[4])
      
      for y in xrange(0, GUI_HEIGHT - 2 * GUI_BORDER_SIZE):
        gimp.pdb["gimp-drawable-set-pixel"](layers["border-left"],  0,                   y, 4, COLORS[0])
        gimp.pdb["gimp-drawable-set-pixel"](layers["border-right"], GUI_BORDER_SIZE - 1, y, 4, COLORS[0])
        for x in xrange(1, 3):
          gimp.pdb["gimp-drawable-set-pixel"](layers["border-left"],  x,                       y, 4, COLORS[5])
          gimp.pdb["gimp-drawable-set-pixel"](layers["border-right"], GUI_BORDER_SIZE - 1 - x, y, 4, COLORS[2])
        for x in xrange(3, 7):
          gimp.pdb["gimp-drawable-set-pixel"](layers["border-left"],  x,                       y, 4, COLORS[4])
          gimp.pdb["gimp-drawable-set-pixel"](layers["border-right"], GUI_BORDER_SIZE - 1 - x, y, 4, COLORS[4])

      for x in xrange(0, 4):
        gimp.pdb["gimp-drawable-set-pixel"](layers["edge-top-right"], x,                   0,                       4, COLORS[0])
        gimp.pdb["gimp-drawable-set-pixel"](layers["edge-top-right"], GUI_BORDER_SIZE - 1, GUI_BORDER_SIZE - 1 - x, 4, COLORS[0])
        for y in xrange(1, 3):
          gimp.pdb["gimp-drawable-set-pixel"](layers["edge-top-right"], x,                       y,                       4, COLORS[5])
          gimp.pdb["gimp-drawable-set-pixel"](layers["edge-top-right"], GUI_BORDER_SIZE - 1 - y, GUI_BORDER_SIZE - 1 - x, 4, COLORS[2])
        for y in xrange(3, 7):
          gimp.pdb["gimp-drawable-set-pixel"](layers["edge-top-right"], x, y, 4, COLORS[4])

      gimp.pdb["gimp-drawable-set-pixel"          ](layers["edge-top-right"], GUI_BORDER_SIZE - 3, 2, 4, COLORS[4])
      gimp.pdb["gimp-drawable-set-pixel"          ](layers["edge-top-right"], GUI_BORDER_SIZE - 3, 1, 4, COLORS[0])
      gimp.pdb["gimp-drawable-set-pixel"          ](layers["edge-top-right"], GUI_BORDER_SIZE - 2, 2, 4, COLORS[0])

      gimp.pdb["gimp-image-set-active-layer"      ](image, layers["edge-top-right"])
      gimp.pdb["gimp-image-select-rectangle"      ](image, CHANNEL_OP_REPLACE, GUI_WIDTH - GUI_BORDER_SIZE, 0, GUI_BORDER_SIZE, GUI_BORDER_SIZE)
      gimp.pdb["gimp-edit-copy"                   ](layers["edge-top-right"])
      gimp.pdb["gimp-image-set-active-layer"      ](image, layers["edge-bottom-left"])
      gimp.pdb["gimp-image-select-rectangle"      ](image, CHANNEL_OP_REPLACE, 0, GUI_HEIGHT - GUI_BORDER_SIZE, GUI_BORDER_SIZE, GUI_BORDER_SIZE)
      gimp.pdb["gimp-floating-sel-anchor"         ](gimp.pdb["gimp-item-transform-flip"](gimp.pdb["gimp-edit-paste"](layers["edge-bottom-left"], True), 0, GUI_HEIGHT - GUI_BORDER_SIZE, GUI_BORDER_SIZE - 1, GUI_HEIGHT - 1))

      for x in xrange(2, 7):
        gimp.pdb["gimp-drawable-set-pixel"](layers["edge-top-left"], x, 0, 4, COLORS[0])
        gimp.pdb["gimp-drawable-set-pixel"](layers["edge-top-left"], 0, x, 4, COLORS[0])
        for y in xrange(3, 7):
          gimp.pdb["gimp-drawable-set-pixel"](layers["edge-top-left"], x, y, 4, COLORS[4])
        for y in xrange(1, 3):
          gimp.pdb["gimp-drawable-set-pixel"](layers["edge-top-left"], x, y, 4, COLORS[5])
          gimp.pdb["gimp-drawable-set-pixel"](layers["edge-top-left"], y, x, 4, COLORS[5])

      gimp.pdb["gimp-drawable-set-pixel"          ](layers["edge-top-left"], 1, 1, 4, COLORS[0])
      gimp.pdb["gimp-drawable-set-pixel"          ](layers["edge-top-left"], 3, 3, 4, COLORS[5])

      gimp.pdb["gimp-image-set-active-layer"      ](image, layers["edge-top-left"])
      gimp.pdb["gimp-image-select-rectangle"      ](image, CHANNEL_OP_REPLACE, 0, 0, GUI_BORDER_SIZE, GUI_BORDER_SIZE)
      gimp.pdb["gimp-edit-copy"                   ](layers["edge-top-left"])
      gimp.pdb["gimp-image-set-active-layer"      ](image, layers["edge-bottom-right"])
      gimp.pdb["gimp-image-select-rectangle"      ](image, CHANNEL_OP_REPLACE, GUI_WIDTH - GUI_BORDER_SIZE, GUI_HEIGHT - GUI_BORDER_SIZE, GUI_BORDER_SIZE, GUI_BORDER_SIZE)
      gimp.pdb["gimp-floating-sel-anchor"         ](gimp.pdb["gimp-item-transform-flip"](gimp.pdb["gimp-edit-paste"](layers["edge-bottom-right"], True), GUI_WIDTH - GUI_BORDER_SIZE, GUI_HEIGHT, GUI_WIDTH, GUI_HEIGHT - GUI_BORDER_SIZE))
      gimp.pdb["gimp-context-set-antialias"       ](False)
      gimp.pdb["gimp-context-set-sample-threshold"](0)
      gimp.pdb["gimp-image-select-color"          ](image, CHANNEL_OP_REPLACE, layers["edge-bottom-right"], tuple(COLORS[5]))
      gimp.set_foreground(tuple(COLORS[2]))
      gimp.pdb["gimp-edit-fill"                   ](layers["edge-bottom-right"], FOREGROUND_FILL)
      gimp.pdb["gimp-selection-none"              ](image)
      gimp.pdb["gimp-image-set-active-layer"      ](image, layers["background"])

      png_image = image.duplicate()
      png_layer = gimp.pdb["gimp-image-merge-visible-layers"](png_image, CLIP_TO_IMAGE)
      gimp.pdb["file-png-save"](png_image, png_layer, png_path, png_path, 0, 9, 1, 1, 1, 1, 1)
      try:
        os.mkdir(working_dir + "/util/gui-xcf")
      except OSError:
        pass
      gimp.pdb["gimp-xcf-save"                    ](0, image, None, xcf_path, xcf_path)

def main():
  for directory in ["blockstates", "lang", "models/block", "models/item", "textures/blocks", "textures/items", "textures/gui/container"]:
    try:
      os.makedirs(assets_path + directory)
    except:
      pass

  if not os.path.exists(assets_path + "lang/en_US.lang"):
    write_file(assets_path + "lang/en_US.lang", "")

  regex = re.compile("public final class .* {\n[\s\S]*?\n{2}")

  # get block and item names from the class files (yes, I hate myself for this as much as you do)
  items = re.findall("public static Item.* (.*);", regex.search(read_file(src_path + "item/ModItems.java")).group(0))
  blocks = re.findall("public static Block.* (.*);", regex.search(read_file(src_path + "block/ModBlocks.java")).group(0))

  guis = {}
  regex = re.compile("(GuiContainer[A-Z][a-z]*)\([\s\S]*super\(.*?(\d+)")
  for filename in os.listdir(src_path + "container"):
    if filename.startswith("GuiContainer") and not filename.endswith("Base.java"):
      match = re.findall(regex, read_file(src_path + "container/" + filename))[0]
      gui_name = match[0].replace("GuiContainer", "")
      gui_name = gui_name[0].lower() + gui_name[1:]
      guis[gui_name] = int(match[1])

  create_item_files(items, "item")
  create_item_files(blocks, "block")
  create_block_files(blocks)
  create_lang_file(items, blocks)
  create_gui_textures(guis)

if __name__ == "__main__":
  main()
