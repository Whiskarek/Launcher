package myapp.gradle

class FileImporter implements Importer {
    private String keystorePath
    private Properties properties

    FileImporter(String filePath, def rootProject) {
        keystorePath = rootProject.file('keystore/Keystore.jks').path
        properties = new Properties()
        properties.load(new FileInputStream(filePath))
    }

    String getFabricKey() {
        return properties["fabricKey"]
    }

    String getAppCenterKey() {
        return properties["appcenterKey"]
    }

    String getKeystoreFilePath() {
        return keystorePath
    }

    String getKeystorePassword() {
        return properties["storePassword"]
    }

    String getKeyAlias() {
        return properties["keyAlias"]
    }

    String getKeyPassword() {
        return properties["keyPassword"]
    }

    boolean canMakeSigning() {
        return true
    }
}