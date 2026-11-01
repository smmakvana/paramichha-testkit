package com.paramichha.testkit.examples;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.constraints.pl.NIP;
import org.hibernate.validator.constraints.pl.PESEL;
import org.hibernate.validator.constraints.pl.REGON;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class GodAnnotation {

    // ─── Jakarta Bean Validation ───────────────────────────────────────────────

    @NotNull
    private String notNullField;

    @Null
    private String nullField;

    @NotEmpty
    private String notEmptyField;

    @NotBlank
    private String notBlankField;

    @Size(min = 2, max = 10)
    private String sizeField;

    @Min(1)
    private int minField;

    @Max(100)
    private int maxField;

    @DecimalMin("0.1")
    private BigDecimal decimalMinField;

    @DecimalMax("999.99")
    private BigDecimal decimalMaxField;

    @Positive
    private int positiveField;

    @PositiveOrZero
    private int positiveOrZeroField;

    @Negative
    private int negativeField;

    @NegativeOrZero
    private int negativeOrZeroField;

    @Digits(integer = 5, fraction = 2)
    private BigDecimal digitsField;

    @Pattern(regexp = "^[a-zA-Z]+$")
    private String patternField;

    @Email
    private String emailField;

    @AssertTrue
    private boolean assertTrueField;

    @AssertFalse
    private boolean assertFalseField;

    @Past
    private LocalDate pastField;

    @PastOrPresent
    private LocalDate pastOrPresentField;

    @Future
    private LocalDate futureField;

    @FutureOrPresent
    private LocalDate futureOrPresentField;

    // ─── Hibernate Validator ───────────────────────────────────────────────────

    @Length(min = 2, max = 50)
    private String lengthField;

    @Range(min = 1, max = 100)
    private long rangeField;

    @URL
    private String urlField;

    @CreditCardNumber
    private String creditCardField;

    @ISBN
    private String isbnField;

    @EAN
    private String eanField;

    @UUID
    private String uuidField;

    @CNPJ
    private String cnpjField;

    @CPF
    private String cpfField;

    @NIP
    private String nipField;

    @PESEL
    private String peselField;

    @REGON
    private String regonField;

    @Mod10Check
    private String mod10Field;

    @Mod11Check
    private String mod11Field;

    @LuhnCheck
    private String luhnField;

    @CodePointLength(min = 2, max = 10)
    private String codePointLengthField;

    @Normalized
    private String normalizedField;

    @UniqueElements
    private List<String> uniqueElementsField;

    public GodAnnotation(String notNullField, String nullField, String notEmptyField, String notBlankField, String sizeField, int minField, int maxField, BigDecimal decimalMinField, BigDecimal decimalMaxField, int positiveField, int positiveOrZeroField, int negativeField, int negativeOrZeroField, BigDecimal digitsField, String patternField, String emailField, boolean assertTrueField, boolean assertFalseField, LocalDate pastField, LocalDate pastOrPresentField, LocalDate futureField, LocalDate futureOrPresentField, String lengthField, long rangeField, String urlField, String creditCardField, String isbnField, String eanField, String uuidField, String cnpjField, String cpfField, String nipField, String peselField, String regonField, String mod10Field, String mod11Field, String luhnField, String codePointLengthField, String normalizedField, List<String> uniqueElementsField) {
        this.notNullField = notNullField;
        this.nullField = nullField;
        this.notEmptyField = notEmptyField;
        this.notBlankField = notBlankField;
        this.sizeField = sizeField;
        this.minField = minField;
        this.maxField = maxField;
        this.decimalMinField = decimalMinField;
        this.decimalMaxField = decimalMaxField;
        this.positiveField = positiveField;
        this.positiveOrZeroField = positiveOrZeroField;
        this.negativeField = negativeField;
        this.negativeOrZeroField = negativeOrZeroField;
        this.digitsField = digitsField;
        this.patternField = patternField;
        this.emailField = emailField;
        this.assertTrueField = assertTrueField;
        this.assertFalseField = assertFalseField;
        this.pastField = pastField;
        this.pastOrPresentField = pastOrPresentField;
        this.futureField = futureField;
        this.futureOrPresentField = futureOrPresentField;
        this.lengthField = lengthField;
        this.rangeField = rangeField;
        this.urlField = urlField;
        this.creditCardField = creditCardField;
        this.isbnField = isbnField;
        this.eanField = eanField;
        this.uuidField = uuidField;
        this.cnpjField = cnpjField;
        this.cpfField = cpfField;
        this.nipField = nipField;
        this.peselField = peselField;
        this.regonField = regonField;
        this.mod10Field = mod10Field;
        this.mod11Field = mod11Field;
        this.luhnField = luhnField;
        this.codePointLengthField = codePointLengthField;
        this.normalizedField = normalizedField;
        this.uniqueElementsField = uniqueElementsField;
    }

    public String getNotNullField() {
        return notNullField;
    }

    public void setNotNullField(String notNullField) {
        this.notNullField = notNullField;
    }
}