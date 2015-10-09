package br.usjt.wesley.exerciciojson.util;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import java.lang.reflect.Field;

import br.usjt.wesley.exerciciojson.R;

public class Util {
    public static Drawable getDrawable(Activity context, String drawableName){
        //procurar a imagem por meio de reflection
        Class<?> c = R.drawable.class;
        try {
            Field idField = c.getDeclaredField(drawableName);
            int id = idField.getInt(idField);
            return context.getResources().getDrawable(id);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
