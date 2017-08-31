package com.phoenixnap.oss.ramlapisync.generation.rules.spring;

import com.phoenixnap.oss.ramlapisync.data.ApiResourceMetadata;
import com.phoenixnap.oss.ramlapisync.generation.rules.Rule;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMod;

/**
 * @author Jens Saade
 */
public class SpringFeignClientServiceIdFieldDeclarationRule implements Rule<JDefinedClass, JFieldVar, ApiResourceMetadata> {
    @Override
    public JFieldVar apply(ApiResourceMetadata metadata, JDefinedClass generatableType) {
        JFieldVar field = generatableType.field(JMod.PUBLIC, String.class, "serviceId");
        return field;
    }
}
