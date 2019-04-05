package nz.net.example.wordart.gen.domain;

import static nz.net.example.wordart.gen.domain.Job.Colour.WHITE;
import static nz.net.example.wordart.gen.domain.Job.Colour.BLACK;
import static nz.net.example.wordart.gen.domain.Job.Colour.GREY;

import java.util.LinkedList;
import java.util.List;

public class Command {

    private List<String> commands;

    private Command(List<String> commands) {
        this.commands = commands;
    }

    public List<String> getCommands() {
        return commands;
    }

    public static class CommandBuilder {

        private static final int POINT_SIZE = 48;
        private static final int MARGIN_SIZE = 25;
        private static final int x = MARGIN_SIZE;
        private static final int y = x + POINT_SIZE;

        private static final String FILL_PROPERTY = "-fill";
        private static final String TILE_PROPERTY = "-tile";
        private static final String ANNOTATE_PROPERTY = "-annotate";
        private static final String STROKE_PROPERTY = "-stroke";
        private static final String SIZE_PROPERTY = "-size";
        private static final String FONT_PROPERTY = "-font";
        private static final String POINT_SIZE_PROPERTY = "-pointsize";

        private final List<String> commands;

        public CommandBuilder(Job.Font font) {
            this.commands = new LinkedList<>();

            commands.add("convert");
            commands.add(SIZE_PROPERTY);
            commands.add("400x100");
            commands.add(String.format("xc:%s", BLACK.getFontColour()));
            commands.add(FONT_PROPERTY);
            commands.add(font.getFontName());

            commands.add(POINT_SIZE_PROPERTY);
            commands.add(Integer.toString(POINT_SIZE));
        }

        public void shadowHard(String text) {

            int shadowOffset = POINT_SIZE / 12;
            commands.add(FILL_PROPERTY);
            commands.add(GREY.toString().toLowerCase());
            commands.add(ANNOTATE_PROPERTY);
            commands.add(String.format("+%d+%d", x - shadowOffset, y - shadowOffset));
            commands.add(text);
        }

        public void shadowReflect(String text) {
            commands.add(FILL_PROPERTY);
            commands.add(GREY.getFontColour());
            commands.add(ANNOTATE_PROPERTY);
            commands.add(String.format("0x45+%d+%d", x, y));
            commands.add(text);
        }

        public void outline() {
            commands.add(STROKE_PROPERTY);
            commands.add(BLACK.getFontColour());
        }

        public void gradient(Job.Colour fontColour) {
            commands.add(TILE_PROPERTY);
            commands.add(String.format("gradient:%s-%s", WHITE.getFontColour(), fontColour.getFontColour()));
        }

        public void noGradient(Job.Colour fontColour) {
            commands.add(FILL_PROPERTY);
            commands.add(fontColour.getFontColour());
        }

        public Command build(String text, Job.Format format) {
            commands.add(ANNOTATE_PROPERTY);
            commands.add(String.format("+%d+%d", x, y));
            commands.add(text);

            commands.add(String.format("%s:-", format.getFormat()));
            return new Command(this.commands);
        }
    }
}