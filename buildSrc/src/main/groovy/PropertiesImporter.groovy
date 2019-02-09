package myapp.gradle

class PropertiesImporter {
    private Importer propImporter

    public PropertiesImporter(String filePath, def rootProject) {
        File file = new File(filePath)
        if (System.getenv("CIRCLECI") == 'true') {
            propImporter = new CIImporter(rootProject)
        } else if (file.exists()) {
            propImporter = new FileImporter(filePath, rootProject)
        } else {
            propImporter = new DefaultImporter()
        }
    }

    String getFabricKey() {
        return propImporter.getFabricKey()
    }

    String getAppCenterKey() {
        return propImporter.getAppCenterKey()
    }

    String getKeystoreFilePath() {
        return propImporter.getKeystoreFilePath()
    }

    String getKeystorePassword() {
        return propImporter.getKeystorePassword()
    }

    String getKeyAlias() {
        return propImporter.getKeyAlias()
    }

    String getKeyPassword() {
        return propImporter.getKeyPassword()
    }

    boolean canMakeSigning() {
        return propImporter.canMakeSigning()
    }
}