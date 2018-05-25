package jp.ac.titech.itpro.sdl.gles10ex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cube implements SimpleRenderer.Obj {

    private final static float[] VERTICES = {
            // left
            -1, -1, -1,
            -1, -1, 1,
            -1, 1, -1,
            -1, 1, 1,
            // right
            1, -1, -1,
            1, -1, 1,
            1, 1, -1,
            1, 1, 1,
            // bottom
            -1, -1, -1,
            1, -1, -1,
            -1, -1, 1,
            1, -1, 1,
            // top
            -1, 1, -1,
            1, 1, -1,
            -1, 1, 1,
            1, 1, 1,
            // back
            -1, -1, -1,
            -1, 1, -1,
            1, -1, -1,
            1, 1, -1,
            // front
            -1, -1, 1,
            -1, 1, 1,
            1, -1, 1,
            1, 1, 1
    };

    private final static float[] TEX_COORDS = {
            // left
            0, 0,
            0, 1,
            1, 0,
            1, 1,
            // right
            0, 0,
            0, 1,
            1, 0,
            1, 1,
            // bottom
            0, 0,
            0, 1,
            1, 0,
            1, 1,
            // top
            0, 0,
            0, 1,
            1, 0,
            1, 1,
            // back
            0, 0,
            0, 1,
            1, 0,
            1, 1,
            // front
            0, 0,
            0, 1,
            1, 0,
            1, 1,
    };

    private final FloatBuffer vbuf;
    private final FloatBuffer tbuf;

    Cube() {
        vbuf = ByteBuffer.allocateDirect(VERTICES.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vbuf.put(VERTICES);
        vbuf.position(0);
        tbuf = ByteBuffer.allocateDirect(TEX_COORDS.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        tbuf.put(TEX_COORDS);
        tbuf.position(0);
    }

    public void draw(GL10 gl, int texture) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vbuf);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, tbuf);

        // left
        gl.glNormal3f(-1, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        // right
        gl.glNormal3f(1, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);

        // bottom
        gl.glNormal3f(0, -1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);

        // top
        gl.glNormal3f(0, 1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);

        // rear
        gl.glNormal3f(0, 0, -1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);

        // front
        gl.glNormal3f(0, 0, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
    }

}
