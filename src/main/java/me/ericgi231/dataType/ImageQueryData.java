package me.ericgi231.dataType;

import me.ericgi231.constant.ActionConstant;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public record ImageQueryData(int quantity, ArrayList<String> terms, ActionConstant.ImageType imageType) {
    @Override
    public int quantity() {
        return quantity;
    }

    @Override
    @Nullable
    public ArrayList<String> terms() {
        return terms;
    }

    @Override
    @Nullable
    public ActionConstant.ImageType imageType() {
        return imageType;
    }

    public boolean hasTerms() {
        return terms != null;
    }
}
