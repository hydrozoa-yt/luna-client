// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Drawable extends QueueNode {

	public static boolean aBoolean1421;
	public static int startX;
	public static int endY;
	public static int startY;
	public static int endX;
	public static int lastPixelX;
	public static int halfWidthX;
	public static int halfHeightY;

	public static int pixels[];
	public static int width;
	public static int height;

	public Drawable() {
	}

	public static void method444(int width, int heíght, int pixels[]) {
		Drawable.pixels = pixels;
		Drawable.width = width;
		Drawable.height = heíght;
		recalcEdges(0, 0, heíght, width, true);
	}

	public static void method445() {
		startY = 0;
		startX = 0;
		endX = width;
		endY = height;
		lastPixelX = endX - 1;
		halfWidthX = endX / 2;
	}

	public static void recalcEdges(int startX, int startY, int endY, int endX, boolean fullrecalc) {
		if (startY < 0)
			startY = 0;
		if (startX < 0)
			startX = 0;
		if (endX > width)
			endX = width;
		if (endY > height)
			endY = height;
		Drawable.startY = startY;
		Drawable.startX = startX;
		Drawable.endX = endX;
		Drawable.endY = endY;
		if (!fullrecalc) {
			return;
		} else {
			lastPixelX = Drawable.endX - 1;
			halfWidthX = Drawable.endX / 2;
			halfHeightY = Drawable.endY / 2;
			return;
		}
	}

	public static void method447(int i) {
		int j = width * height;
		if (i != 4)
			aBoolean1421 = !aBoolean1421;
		for (int k = 0; k < j; k++)
			pixels[k] = 0;

	}

	public static void method448(boolean flag, int i, int j, int k, int l, int i1, int j1) {
		if (j1 < startY) {
			k -= startY - j1;
			j1 = startY;
		}
		if (j < startX) {
			l -= startX - j;
			j = startX;
		}
		if (j1 + k > endX)
			k = endX - j1;
		if (j + l > endY)
			l = endY - j;
		int k1 = 256 - i1;
		int l1 = (i >> 16 & 0xff) * i1;
		int i2 = (i >> 8 & 0xff) * i1;
		int j2 = (i & 0xff) * i1;
		if (flag)
			aBoolean1421 = !aBoolean1421;
		int j3 = width - k;
		int k3 = j1 + j * width;
		for (int l3 = 0; l3 < l; l3++) {
			for (int i4 = -k; i4 < 0; i4++) {
				int k2 = (pixels[k3] >> 16 & 0xff) * k1;
				int l2 = (pixels[k3] >> 8 & 0xff) * k1;
				int i3 = (pixels[k3] & 0xff) * k1;
				int j4 = ((l1 + k2 >> 8) << 16) + ((i2 + l2 >> 8) << 8) + (j2 + i3 >> 8);
				pixels[k3++] = j4;
			}

			k3 += j3;
		}

	}

	public static void method449(int i, int j, int k, byte byte0, int l, int i1) {
		if (i1 < startY) {
			l -= startY - i1;
			i1 = startY;
		}
		if (j < startX) {
			i -= startX - j;
			j = startX;
		}
		if (i1 + l > endX)
			l = endX - i1;
		if (j + i > endY)
			i = endY - j;
		int j1 = width - l;
		int k1 = i1 + j * width;
		for (int l1 = -i; l1 < 0; l1++) {
			for (int i2 = -l; i2 < 0; i2++)
				pixels[k1++] = k;

			k1 += j1;
		}

		if (byte0 == -24)
			;
	}

	public static void method450(int i, int j, int k, int l, int i1, int j1) {
		method452(i1, l, j, j1, true);
		method452(i1, l, (j + k) - 1, j1, true);
		method454(i1, l, k, false, j);
		method454((i1 + j1) - 1, l, k, false, j);
	}

	public static void method451(int i, int j, int k, int l, int i1, int j1, byte byte0) {
		if (byte0 != -113)
			return;
		method453(i1, i, j, 1388, j1, k);
		method453((i1 + l) - 1, i, j, 1388, j1, k);
		if (l >= 3) {
			method455(0, i1 + 1, i, k, l - 2, j1);
			method455(0, i1 + 1, (i + j) - 1, k, l - 2, j1);
		}
	}

	public static void method452(int i, int j, int k, int l, boolean flag) {
		if (k < startX || k >= endY)
			return;
		if (i < startY) {
			l -= startY - i;
			i = startY;
		}
		if (i + l > endX)
			l = endX - i;
		int i1 = i + k * width;
		if (!flag) {
			for (int j1 = 1; j1 > 0; j1++);
		}
		for (int k1 = 0; k1 < l; k1++)
			pixels[i1 + k1] = j;

	}

	public static void method453(int i, int j, int k, int l, int i1, int j1) {
		if (i < startX || i >= endY)
			return;
		if (j < startY) {
			k -= startY - j;
			j = startY;
		}
		if (j + k > endX)
			k = endX - j;
		int k1 = 256 - i1;
		int l1 = (j1 >> 16 & 0xff) * i1;
		int i2 = (j1 >> 8 & 0xff) * i1;
		int j2 = (j1 & 0xff) * i1;
		int j3 = j + i * width;
		for (int k3 = 0; k3 < k; k3++) {
			int k2 = (pixels[j3] >> 16 & 0xff) * k1;
			int l2 = (pixels[j3] >> 8 & 0xff) * k1;
			int i3 = (pixels[j3] & 0xff) * k1;
			int l3 = ((l1 + k2 >> 8) << 16) + ((i2 + l2 >> 8) << 8) + (j2 + i3 >> 8);
			pixels[j3++] = l3;
		}
	}

	public static void method454(int i, int j, int k, boolean flag, int l) {
		if (flag)
			return;
		if (i < startY || i >= endX)
			return;
		if (l < startX) {
			k -= startX - l;
			l = startX;
		}
		if (l + k > endY)
			k = endY - l;
		int i1 = i + l * width;
		for (int j1 = 0; j1 < k; j1++)
			pixels[i1 + j1 * width] = j;

	}

	public static void method455(int i, int width, int k, int l, int i1, int darkness) {
		if (k < startY || k >= endX)
			return;
		if (width < startX) {
			i1 -= startX - width;
			width = startX;
		}
		if (width + i1 > endY)
			i1 = endY - width;
		int saturation = 256 - darkness;
		int rbgComponent1_1 = (l >> 16 & 0xff) * darkness;
		int rbgComponent2_1 = (l >> 8 & 0xff) * darkness;
		int rbgComponent3_1 = (l & 0xff) * darkness;
		if (i != 0) {
			for (int j3 = 1; j3 > 0; j3++);
		}
		int pixelOffset = k + width * Drawable.width;
		for (int l3 = 0; l3 < i1; l3++) {
			int rgbComponent1_2 = (pixels[pixelOffset] >> 16 & 0xff) * saturation;
			int rgbComponent2_2 = (pixels[pixelOffset] >> 8 & 0xff) * saturation;
			int rbgComponent3_2 = (pixels[pixelOffset] & 0xff) * saturation;
			int pixelColor = ((rbgComponent1_1 + rgbComponent1_2 >> 8) << 16) + ((rbgComponent2_1 + rgbComponent2_2 >> 8) << 8) + (rbgComponent3_1 + rbgComponent3_2 >> 8);
			pixels[pixelOffset] = pixelColor;
			pixelOffset += Drawable.width;
		}

	}
}
