package io.lucasvalenteds.petstore.store.store;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import io.lucasvalenteds.petstore.store.analytics.Analytics;
import io.lucasvalenteds.petstore.store.jobs.Jobs;
import io.lucasvalenteds.petstore.store.management.Management;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class StoreModuleTest {

    private final Injector injector = Guice.createInjector(new StoreModule());

    private static Stream<Arguments> capabilitiesByType() {
        return Stream.of(
            Arguments.of(Analytics.class),
            Arguments.of(Jobs.class),
            Arguments.of(Management.class)
        );
    }

    @ParameterizedTest
    @MethodSource("capabilitiesByType")
    public void testAllCapabilitiesCanBeRetrievedIndividually(Class capability) {
        Store store = injector.getInstance(Store.class);

        assertNotEquals(
            injector.getBinding(capability),
            store.getCapability(capability).get()
        );
    }

    private static Stream<Arguments> capabilitiesByName() {
        return Stream.of(
            Arguments.of("analytics"),
            Arguments.of("jobs"),
            Arguments.of("management")
        );
    }

    @ParameterizedTest
    @MethodSource("capabilitiesByName")
    public void testAllCapabilitiesCanBeRetrievedByName(String name) {
        assertNotNull(injector.getInstance(Key.get(Capability.class, Names.named(name))));
    }

    @Test
    public void testAllCapabilitiesCanBeRetrievedAsCollection() {
        assertNotNull(injector.findBindingsByType(TypeLiteral.get(Capability.class)));
    }

    @Test
    public void testStoreImplementationCanBeCreated() {
        assertNotNull(injector.getInstance(Store.class));
        assertNotNull(injector.getInstance(PetStore.class));
    }
}
