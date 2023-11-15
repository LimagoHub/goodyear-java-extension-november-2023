import de.goodyear.translator.Translator;
import de.goodyear.translator.inner.ToLowerTranslatorImpl;
import de.goodyear.translator.inner.ToUpperTranslatorImpl;

module dependency {
    exports de.goodyear.translator;

    uses Translator;
    provides Translator with ToUpperTranslatorImpl, ToLowerTranslatorImpl;
}