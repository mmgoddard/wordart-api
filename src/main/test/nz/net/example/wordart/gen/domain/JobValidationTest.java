package nz.net.example.wordart.gen.domain;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.EnumSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static nz.net.example.wordart.gen.domain.Job.Colour.BLACK;
import static nz.net.example.wordart.gen.domain.Job.Effect.OUTLINE;
import static nz.net.example.wordart.gen.domain.Job.Font.ARIAL;
import static nz.net.example.wordart.gen.domain.Job.Format;
import static org.junit.Assert.assertTrue;

public class JobValidationTest {

    private Validator validator;
    private Set<ConstraintViolation<Job>> violations;
    private Job job;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void defaultValidationShouldPass() {
        job = new Job(Format.PNG, "te 86!%$'()*?", EnumSet.of(OUTLINE), BLACK, ARIAL);
        violations = validator.validate(job);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void nullFormatShouldFailValidation() {
        job = new Job(null, "text", EnumSet.of(OUTLINE), BLACK, ARIAL);
        violations = validator.validate(job);
        assertEquals(1, violations.size());
    }

    @Test
    public void nullEffectsShouldFailValidation() {
        job = new Job(Format.PNG, "text", null, BLACK, ARIAL);
        violations = validator.validate(job);
        assertEquals(1, violations.size());
    }

    @Test
    public void nullFontColourShouldFailValidation() {
        job = new Job(Format.PNG, "text", EnumSet.of(OUTLINE), null, ARIAL);
        violations = validator.validate(job);
        assertEquals(1, violations.size());
    }

    @Test
    public void nullFontShouldFailValidation() {
        job = new Job(Format.PNG, "text", EnumSet.of(OUTLINE), BLACK, null);
        violations = validator.validate(job);
        assertEquals(1, violations.size());
    }

    @Test
    public void nullTextShouldFailValidation() {
        job = new Job(Format.PNG, null, EnumSet.of(OUTLINE), BLACK, ARIAL);
        violations = validator.validate(job);
        assertEquals(1, violations.size());
    }

    @Test
    public void invalidCharactersTextShouldFailValidation() {
        job = new Job(Format.PNG, "><£^|", EnumSet.of(OUTLINE), BLACK, ARIAL);
        violations = validator.validate(job);
        assertEquals(1, violations.size());
    }

    @Test
    public void emptyTextShouldFailValidation() {
        job = new Job(Format.PNG, "", EnumSet.of(OUTLINE), BLACK, ARIAL);
        violations = validator.validate(job);
        assertEquals(1, violations.size());
    }

    @Test
    public void maximumLengthTextShouldFailValidationd() {
        job = new Job(Format.PNG, "hjhgfgrtfgtrfgtrfgtrk", EnumSet.of(OUTLINE), BLACK, ARIAL);
        violations = validator.validate(job);
        assertEquals(1, violations.size());
    }
}