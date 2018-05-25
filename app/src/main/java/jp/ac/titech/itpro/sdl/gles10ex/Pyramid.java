package jp.ac.titech.itpro.sdl.gles10ex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Pyramid implements SimpleRenderer.Obj {

    private final static float[] VERTICES = {
            // bottom
            -1, 0, -1,
            1, 0, -1,
            -1, 0, 1,
            1, 0, 1,
            // left
            -1, 0, -1,
            0, 1, 0,
            -1, 0, 1,
            // right
            1, 0, -1,
            0, 1, 0,
            1, 0, 1,
            // back
            -1, 0, -1,
            0, 1, 0,
            1, 0, -1,
            // front
            -1, 0, 1,
            0, 1, 0,
            1, 0, 1
    };

    private final static float[] TEX_COORDS = {
            // bottom
            0, 0,
            0, 1,
            1, 0,
            1, 1,
            // left
            0, 0,
            0, 1,
            1, 0,
            // right
            0, 0,
            0, 1,
            1, 0,
            // back
            0, 0,
            0, 1,
            1, 0,
            // front
            0, 0,
            0, 1,
            1, 0,
    };

    private final FloatBuffer vbuf;
    private final FloatBuffer tbuf;

    Pyramid() {
        vbuf = ByteBuffer.allocateDirect(VERTICES.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vbuf.put(VERTICES);
        vbuf.position(0);
        tbuf = ByteBuffer.allocateDirect(TEX_COORDS.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        tbuf.put(TEX_COORDS);
        tbuf.position(0);
    }

    @Override
    public void draw(GL10 gl, int texture) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vbuf);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, tbuf);

        // bottom
        gl.glNormal3f(0, -1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        // left
        gl.glNormal3f(-1, 1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 4, 3);

        // right
        gl.glNormal3f(1, 1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 7, 3);

        // back
        gl.glNormal3f(0, 1, -1);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 10, 3);

        // front
        gl.glNormal3f(0, 1, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 13, 3);
    }
}
