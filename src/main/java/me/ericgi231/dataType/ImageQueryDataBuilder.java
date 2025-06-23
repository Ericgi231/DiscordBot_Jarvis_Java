package me.ericgi231.dataType;

import me.ericgi231.constant.ActionConstant;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ImageQueryDataBuilder {
    private int quantity;
    private ArrayList<String> terms;
    private ActionConstant.ImageType imageType;

    public ImageQueryDataBuilder() {
        quantity = 1;
        terms = new ArrayList<>();
        imageType = ActionConstant.ImageType.STOCK;
    }

    @NotNull
    public ImageQueryData build(){
        return new ImageQueryData(quantity, terms, imageType);
    }

    @NotNull
    public ImageQueryDataBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @NotNull
    public ImageQueryDataBuilder addTerm(@NotNull String term) {
        this.terms.add(term);
        return this;
    }

    @NotNull
    public ImageQueryDataBuilder setTerms(@NotNull ArrayList<String> terms) {
        this.terms = terms;
        return this;
    }

    @NotNull
    public ImageQueryDataBuilder setImageType(@NotNull ActionConstant.ImageType imageType) {
        this.imageType = imageType;
        return this;
    }
}
