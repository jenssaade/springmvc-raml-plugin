package com.phoenixnap.oss.ramlapisync.raml.jrp.raml08v1;

import com.phoenixnap.oss.ramlapisync.raml.RamlQueryParameter;
import org.raml.model.ParamType;
import org.raml.model.parameter.QueryParameter;

import java.math.BigDecimal;

/**
 * @author armin.weisser
 */
public class Jrp08V1RamlQueryParameter extends RamlQueryParameter {

    private final QueryParameter queryParameter;

    public Jrp08V1RamlQueryParameter(QueryParameter queryParameter) {
        this.queryParameter = queryParameter;
    }

    /**
     * Expose internal representation only package private
     * @return the internal model
     */
    QueryParameter getQueryParameter() {
        return queryParameter;

    }

    @Override
    public void setType(ParamType paramType) {
        queryParameter.setType(paramType);
    }

    @Override
    public void setRequired(boolean required) {
        queryParameter.setRequired(required);
    }

    @Override
    public void setExample(String example) {
        queryParameter.setExample(example);
    }

    @Override
    public void setDescription(String description) {
        queryParameter.setDescription(description);
    }

    @Override
    public boolean isRequired() {
        return queryParameter.isRequired();
    }

    @Override
    public ParamType getType() {
        return queryParameter.getType();
    }

    @Override
    public String getExample() {
        return queryParameter.getExample();
    }

    @Override
    public void setDisplayName(String displayName) {
        queryParameter.setDisplayName(displayName);
    }

    @Override
    public String getDescription() {
        return queryParameter.getDescription();
    }

    @Override
    public void setRepeat(boolean repeat) {
        queryParameter.setRepeat(repeat);
    }

    @Override
    public Integer getMinLength() {
        return queryParameter.getMinLength();
    }

    @Override
    public Integer getMaxLength() {
        return queryParameter.getMaxLength();
    }

    @Override
    public BigDecimal getMinimum() {
        return queryParameter.getMinimum();
    }

    @Override
    public BigDecimal getMaximum() {
        return queryParameter.getMaximum();
    }

    @Override
    public String getPattern() {
        return queryParameter.getPattern();
    }

    @Override
    public String getDisplayName() {
        return queryParameter.getDisplayName();
    }

    @Override
    public boolean isRepeat() {
        return queryParameter.isRepeat();
    }
}
