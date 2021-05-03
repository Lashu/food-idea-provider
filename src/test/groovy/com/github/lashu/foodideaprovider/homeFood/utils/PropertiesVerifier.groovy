package com.github.lashu.foodideaprovider.homeFood.utils

class PropertiesVerifier {

    static void verifyCustomProperties(Map<String, ?> properties, Set<String> allowedProperties) {
        def illegalProperties = properties.keySet() - allowedProperties
        if (!illegalProperties.empty) {
            throw new IllegalPropertiesException(illegalProperties)
        }
    }

    private static class IllegalPropertiesException extends RuntimeException {
        IllegalPropertiesException(Set<String> illegalProperties) {
            super("Validation failed. Unknown properties: " + illegalProperties)
        }
    }

}
