import com.android.builder.signing.DefaultSigningConfig
import java.io.File
import java.io.FileInputStream
import java.util.*

/**
 * The [propsFile] must contain following properties:
 * storeFile - the path to the keystore file
 * storePassword - store password
 * keyAlias - key alias
 * keyPassword - key password
 */
fun DefaultSigningConfig.configFrom(propsFile: File) {
    if (propsFile.exists()) {
        val props = Properties()
        props.load(FileInputStream(propsFile))
        storeFile = File(props.getProperty("storeFile"))
        storePassword = props.getProperty("storePassword")
        keyAlias = props.getProperty("keyAlias")
        keyPassword = props.getProperty("keyPassword")
    } else {
        System.err.println("Signing config for $name not found at ${propsFile.path}")
    }
}