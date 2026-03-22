// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class IndexedSprite extends Drawable {

	public byte pixels_1516[];
	public int pixelColours_1517[];
	public int width_1518;
	public int height_1519;
	public int attrib_1520;
	public int attrib_1521;
	public int width_1522;
	public int height_1523;

	/**
	 *
	 * @param archive
	 * @param name				name of the sprite
	 * @param page		array index of the sprite, somehow
	 */
	public IndexedSprite(Archive archive, String name, int page) {
		//System.out.println("Loading "+name);
		JagBuffer dataBuf = new JagBuffer(archive.get(name + ".dat"));
		JagBuffer idxBuf = new JagBuffer(archive.get("index.dat"));
		idxBuf.position = dataBuf.getShort();
		width_1522 = idxBuf.getShort();
		height_1523 = idxBuf.getShort();
		int colorLength = idxBuf.getByte();
		pixelColours_1517 = new int[colorLength];
		for (int colorIndex = 0; colorIndex < colorLength - 1; colorIndex++) {
			pixelColours_1517[colorIndex + 1] = idxBuf.getTriByte();
			//System.out.println(name+": pixelColor["+(colorIndex+1)+"] = "+pixelColours_1517[colorIndex + 1]);
		}

		for (int l = 0; l < page; l++) {
			idxBuf.position += 2;
			dataBuf.position += idxBuf.getShort() * idxBuf.getShort();
			idxBuf.position++;
		}

		attrib_1520 = idxBuf.getByte();
		//System.out.println(name+": unknownByte1="+ attrib_1520);
		attrib_1521 = idxBuf.getByte();
		//System.out.println(name+": unknownByte2="+ attrib_1521);
		width_1518 = idxBuf.getShort();
		//System.out.println(name+": width_1518="+width_1518);
		height_1519 = idxBuf.getShort();
		//System.out.println(name+": height_1519="+height_1519);
		int typeByte_i1 = idxBuf.getByte();
		//System.out.println(name+": typeByte_i1="+typeByte_i1);
		int pixelsLength = width_1518 * height_1519;
		pixels_1516 = new byte[pixelsLength];
		if (typeByte_i1 == 0) {
			for (int k1 = 0; k1 < pixelsLength; k1++) {
				pixels_1516[k1] = dataBuf.getSignedByte();
			}
			return;
		}
		if (typeByte_i1 == 1) {
			for (int x = 0; x < width_1518; x++) {
				for (int y = 0; y < height_1519; y++) {
					pixels_1516[x + y * width_1518] = dataBuf.getSignedByte();
				}
			}
		}
	}

	public void method485(int i) {
		width_1522 /= 2;
		height_1523 /= 2;
		byte abyte0[] = new byte[width_1522 * height_1523];
		int j = 0;
		if (i != 0)
			return;
		for (int k = 0; k < height_1519; k++) {
			for (int l = 0; l < width_1518; l++) {
				abyte0[(l + attrib_1520 >> 1) + (k + attrib_1521 >> 1) * width_1522] = pixels_1516[j++];
			}
		}

		pixels_1516 = abyte0;
		width_1518 = width_1522;
		height_1519 = height_1523;
		attrib_1520 = 0;
		attrib_1521 = 0;
	}

	public void method486(boolean flag) {
		if (width_1518 == width_1522 && height_1519 == height_1523)
			return;
		byte abyte0[] = new byte[width_1522 * height_1523];
		int i = 0;
		for (int j = 0; j < height_1519; j++) {
			for (int k = 0; k < width_1518; k++)
				abyte0[k + attrib_1520 + (j + attrib_1521) * width_1522] = pixels_1516[i++];

		}

		pixels_1516 = abyte0;
		width_1518 = width_1522;
		if (!flag) {
			return;
		} else {
			height_1519 = height_1523;
			attrib_1520 = 0;
			attrib_1521 = 0;
			return;
		}
	}

	public void method487(int i) {
		byte abyte0[] = new byte[width_1518 * height_1519];
		int j = 0;
		for (int k = 0; k < height_1519; k++) {
			for (int l = width_1518 - 1; l >= 0; l--)
				abyte0[j++] = pixels_1516[l + k * width_1518];

		}

		pixels_1516 = abyte0;
		if (i != 0) {
			return;
		} else {
			attrib_1520 = width_1522 - width_1518 - attrib_1520;
			return;
		}
	}

	public void method488(byte byte0) {
		byte abyte0[] = new byte[width_1518 * height_1519];
		int i = 0;
		for (int j = height_1519 - 1; j >= 0; j--) {
			for (int k = 0; k < width_1518; k++) {
                abyte0[i++] = pixels_1516[k + j * width_1518];
            }
		}
		pixels_1516 = abyte0;
		attrib_1521 = height_1523 - height_1519 - attrib_1521;
	}

	public void rgbAdjust_489(int blueAdjust_i, int greenAdjust_j, int redAdjust_k, int unused_l) {
		for (int index_i1 = 0; index_i1 < pixelColours_1517.length; index_i1++) {
			int red_j1 = pixelColours_1517[index_i1] >> 16 & 0xff;
			red_j1 += redAdjust_k;
			if (red_j1 < 0)
				red_j1 = 0;
			else if (red_j1 > 255)
				red_j1 = 255;
			int green_k1 = pixelColours_1517[index_i1] >> 8 & 0xff;
			green_k1 += greenAdjust_j;
			if (green_k1 < 0)
				green_k1 = 0;
			else if (green_k1 > 255)
				green_k1 = 255;
			int blue_l1 = pixelColours_1517[index_i1] & 0xff;
			blue_l1 += blueAdjust_i;
			if (blue_l1 < 0)
				blue_l1 = 0;
			else if (blue_l1 > 255)
				blue_l1 = 255;
			pixelColours_1517[index_i1] = (red_j1 << 16) + (green_k1 << 8) + blue_l1;
		}
	}

	public void draw_490(int x, int y) {
		y += attrib_1520;
		x += attrib_1521;
		int i1 = y + x * Drawable.width;
		int j1 = 0;
		int height_k1 = height_1519;
		int width_l1 = width_1518;
		int widthDiff = Drawable.width - width_l1;
		int j2 = 0;
		if (x < Drawable.startX) {
			int k2 = Drawable.startX - x;
			height_k1 -= k2;
			x = Drawable.startX;
			j1 += k2 * width_l1;
			i1 += k2 * Drawable.width;
		}
		if (x + height_k1 > Drawable.endY)
			height_k1 -= (x + height_k1) - Drawable.endY;
		if (y < Drawable.startY) {
			int l2 = Drawable.startY - y;
			width_l1 -= l2;
			y = Drawable.startY;
			j1 += l2;
			i1 += l2;
			j2 += l2;
			widthDiff += l2;
		}
		if (y + width_l1 > Drawable.endX) {
			int i3 = (y + width_l1) - Drawable.endX;
			width_l1 -= i3;
			j2 += i3;
			widthDiff += i3;
		}
        if (width_l1 > 0 && height_k1 > 0) {
            drawRelated_491(pixelColours_1517, Drawable.pixels, j1, pixels_1516, j2, height_k1, width_l1, i1, widthDiff);
        }
    }

	public void drawRelated_491(int[] palette, int[] dest, int i, byte[] content, int j, int k, int l, int i1, int j1) {
		int k1 = -(l >> 2);
		l = -(l & 3);
		for (int l1 = -k; l1 < 0; l1++) {
			for (int i2 = k1; i2 < 0; i2++) {
				byte byte0 = content[i++];
				if (byte0 != 0) {
                    dest[i1++] = palette[byte0 & 0xff];
                } else {
                    i1++;
                }
				byte0 = content[i++];
				if (byte0 != 0) {
                    dest[i1++] = palette[byte0 & 0xff];
                } else {
                    i1++;
                }
				byte0 = content[i++];
				if (byte0 != 0) {
                    dest[i1++] = palette[byte0 & 0xff];
                } else {
                    i1++;
                }
				byte0 = content[i++];
				if (byte0 != 0) {
                    dest[i1++] = palette[byte0 & 0xff];
                } else {
                    i1++;
                }
			}

			for (int j2 = l; j2 < 0; j2++) {
				byte byte1 = content[i++];
				if (byte1 != 0) {
                    dest[i1++] = palette[byte1 & 0xff];
                } else {
                    i1++;
                }
			}
			i1 += j1;
			i += j;
		}

	}
}
