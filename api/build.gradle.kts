import net.labymod.labygradle.common.extension.LabyModAnnotationProcessorExtension.ReferenceType

dependencies {
    labyProcessor()
    labyApi("api")
    addonMavenDependency("com.rappytv.globaltags:GlobalTagsJava:1.1.3")
}

labyModAnnotationProcessor {
    referenceType = ReferenceType.INTERFACE
}