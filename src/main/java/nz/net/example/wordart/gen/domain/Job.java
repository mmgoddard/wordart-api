package nz.net.example.wordart.gen.domain;

import org.springframework.http.MediaType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.EnumSet;

public class Job {

    /**
     * Supported output formats
     */
    public enum Format {
        PNG("png", MediaType.IMAGE_PNG),
        JPEG("jpeg", MediaType.IMAGE_JPEG),
        TIFF("tiff", MediaType.valueOf("image/tiff")),
        ASCII("ascii", MediaType.valueOf("image/ascii"));

        private String format;
        private MediaType mediaType;

        Format(String format, MediaType mediaType) {
            this.format = format;
            this.mediaType = mediaType;
        }

        public String getFormat() {
            return format;
        }
        public MediaType getMediaType() {
            return mediaType;
        }
    }

    /**
     * Font effects for rendered output
     */
    public enum Effect {
        SHADOW_HARD,
        SHADOW_REFLECT,
        GRADIENT,
        OUTLINE
    }

    /**
     * Supported colours
     */
    public enum Colour {
        RED("red"),
        GREEN("green"),
        BLUE("blue"),
        BLACK("black"),
        GREY("grey"),
        WHITE("white");

        private String fontColour;

        Colour(String fontColour) {
            this.fontColour = fontColour;
        }

        public String getFontColour() {
            return fontColour;
        }
    }

    /**
     * Support fonts for word art
     */
    public enum Font {
        FREE_MONO("FreeMono"),
        ARIAL("Arial");

        private String fontName;

        Font(String fontName) {
            this.fontName = fontName;
        }

        public String getFontName() {
            return fontName;
        }
    }

    private final static String NOT_NULL_ERR_MSG = "cannot be null";

    @NotNull(message = NOT_NULL_ERR_MSG)
    private final Format format;

    @Pattern(regexp = "^[A-Za-z0-9 !%$'()*?]{1,20}$")
    @NotNull(message = NOT_NULL_ERR_MSG)
    private final String text;

    @NotNull(message = NOT_NULL_ERR_MSG)
    private final EnumSet<Effect> effects;

    @NotNull(message = NOT_NULL_ERR_MSG)
    private final Colour fontColour;

    @NotNull(message = NOT_NULL_ERR_MSG)
    private final Font font;

    /**
     * Create a new job specification
     *
     * @param format     file output format for the result.
     * @param text       the text that is to be rendered
     * @param effects    the set of effects that you want applied to your output
     *                   text
     * @param fontColour the fontColour to use when rendering the font
     * @param font       the font face to use when rendering the font
     */
    public Job(Format format, String text, EnumSet<Effect> effects, Colour fontColour, Font font) {
        this.format = format;
        this.text = text;
        this.effects = effects;
        this.fontColour = fontColour;
        this.font = font;
    }

    public Format getFormat() {
        return format;
    }

    public String getText() {
        return text;
    }

    public EnumSet<Effect> getEffects() {
        return effects;
    }

    public Colour getFontColour() {
        return fontColour;
    }

    public Font getFont() {
        return font;
    }

    @Override
    public String toString() {
        return "Job{" +
                "format=" + format +
                ", text='" + text + '\'' +
                ", effects=" + effects +
                ", fontColour=" + fontColour +
                ", font=" + font +
                '}';
    }
}