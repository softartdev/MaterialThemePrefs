#!/usr/bin/env python3
"""
convert_images_to_gif.py

This script takes all PNG files in the current directory, sorts them alphabetically,
resizes each image to a fixed height (default 500px), and combines them into a single
animated GIF called 'demo.gif', displaying each frame for 1 second (1 fps).
"""

import os
from PIL import Image

# -----------------------------------------------------------------------------
# CONSTANTS
# -----------------------------------------------------------------------------
# Height of the resulting GIF, in pixels (maintains aspect ratio for width)
HEIGHT = 500

# Name of the output GIF file
OUTPUT_GIF = "demo.gif"

# File extension filter (only PNG files in this example)
EXTENSION = ".png"


def main():
    # 1) List all files with the specified extension in the current directory
    files = [fname for fname in os.listdir(".") if fname.lower().endswith(EXTENSION)]
    files.sort()  # Sort alphabetically

    if not files:
        print(f"No '{EXTENSION}' files found in the current directory.")
        return

    frames = []
    for fname in files:
        # 2) Open each image
        img = Image.open(fname)

        # 3) Resize to the specified HEIGHT, maintaining aspect ratio
        original_width, original_height = img.size
        new_height = HEIGHT
        new_width = int(original_width * new_height / original_height)
        img_resized = img.resize((new_width, new_height), Image.LANCZOS)

        # 4) Convert to paletted ("P") mode for GIF compatibility
        frames.append(img_resized.convert("P", palette=Image.ADAPTIVE))

    # 5) Save frames as an animated GIF
    #    - duration=1000ms per frame (1 second)
    #    - loop=0 (infinite loop)
    try:
        frames[0].save(
            OUTPUT_GIF,
            format="GIF",
            save_all=True,
            append_images=frames[1:],
            duration=1000,  # milliseconds per frame
            loop=0
        )
        print(f"Saved GIF as '{OUTPUT_GIF}'.")
    except Exception as e:
        print(f"Error while saving GIF: {e}")


if __name__ == "__main__":
    main()
