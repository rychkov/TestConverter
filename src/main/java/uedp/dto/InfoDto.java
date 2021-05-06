package uedp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class InfoDto {
    @JsonProperty
    private Long id;
    @JsonProperty
    private BigDecimal amount;
    @JsonProperty
    private String currency;
    @JsonProperty
    private String comment;
    @JsonProperty
    private String filename;
    @JsonProperty
    private Long line;
    @JsonProperty
    private String result;

    public InfoDto(Long id, BigDecimal amount, String currency, String comment, String filename, Long line, String result) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
        this.filename = filename;
        this.line = line;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getComment() {
        return comment;
    }

    public String getFilename() {
        return filename;
    }

    public Long getLine() {
        return line;
    }

    public String getResult() {
        return result;
    }
}
