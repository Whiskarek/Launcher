package myapp.gradle

class DefaultImporter implements Importer {
    String getFabricKey() {
        return '0000000000000000000000000000000000000000'
    }

    String getAppCenterKey() {
        return '00000000-0000-0000-0000-000000000000'
    }

    String getKeystoreFilePath() {
        return null
    }

    String getKeystorePassword() {
        return null
    }

    String getKeyAlias() {
        return null
    }

    String getKeyPassword() {
        return null
    }

    boolean canMakeSigning() {
        return false
    }
}