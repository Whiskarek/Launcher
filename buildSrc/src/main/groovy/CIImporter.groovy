package myapp.gradle

class CIImporter implements Importer {
    private String keystorePath

    CIImporter(def rootProject) {
        keystorePath = rootProject.file('keystore/Keystore.jks').path
    }

    String getFabricKey() {
        return System.getenv("FABRIC_API_KEY")
    }

    String getAppCenterKey() {
        return System.getenv("APPCENTER_KEY")
    }

    String getKeystoreFilePath() {
        return keystorePath
    }

    String getKeystorePassword() {
        return System.getenv("STORE_PASSWORD")
    }

    String getKeyAlias() {
        return System.getenv("KEY_ALIAS")
    }

    String getKeyPassword() {
        return System.getenv("KEY_PASSWORD")
    }

    boolean canMakeSigning() {
        return true
    }
}