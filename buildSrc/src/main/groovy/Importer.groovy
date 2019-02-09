package myapp.gradle

interface Importer {
    String getFabricKey()
    String getAppCenterKey()
    String getKeystoreFilePath()
    String getKeystorePassword()
    String getKeyAlias()
    String getKeyPassword()
    boolean canMakeSigning()
}