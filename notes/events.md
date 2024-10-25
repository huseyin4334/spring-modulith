# Events
Modules will communicate with each other through events. Events are a way to send messages between modules. 
Events are asynchronous and can be sent from any module to any other module. 

# Send Events

```java
import org.springframework.context.ApplicationEventPublisher;

ApplicationEventPublisher eventPublisher;

void sendEvent() {
    eventPublisher.publishEvent(new MyEvent(this, "Hello"));
}
```

## Listen Transaction Events

```java
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

ApplicationEventPublisher eventPublisher;

@Transactional
public void createOrder() {
    eventPublisher.publishEvent(new OrderCreatedEvent(this, "Hello"));
}

@TransactionalEventListener
public void orderCreatedAndTransactionCommitted(OrderCreatedEvent event) {
    System.out.println("Order created: " + event.getMessage());
}
```

Every listener is wrapped into an aspect that marks the listener succeeded or failed.
If the listener fails, entry will be untouched and the listener will retry if defined retry mechanism.
We can set retry event listeners with `spring.modulith.republish-outstanding-events-on-restart` property. (![Variables](https://docs.spring.io/spring-modulith/reference/appendix.html#configuration-properties))


## Event Publication
Events publish artifacts in `spring-modulith-events-api` this package.

> ![Event Publication Doc](https://docs.spring.io/spring-modulith/reference/events.html#publication-registry.managing-publications)

We have 2 abstraction in this package:
- `CompletedEventPublications`
  - This interface allows accessing all completed event publications, and provides an API to immediately purge all of them from the database or the completed publications older than a given duration (for example, 1 minute).
- `IncompleteEventPublications`
  - This interface allows accessing all incomplete event publications to resubmit either the ones matching a given predicate or older than a given Duration relative to the original publishing date.

If we want to open our events to the outside, we can use some packages too like `spring-modulith-events-kafka`.
Uses Spring Kafka for the interaction with the broker. The logical routing key will be used as Kafka’s topic and message key.



# Test Published Events

```java
@ApplicationModuleTest
class OrderIntegrationTests {

  @Test
  void someTestMethod(PublishedEvents events, AssertablePublishedEvents events) {
    var matchingMapped = events.ofType(OrderCompleted.class)
      .matching(OrderCompleted::getOrderId, reference.getId());

    assertThat(matchingMapped).hasSize(1);

    assertThat(events)
            .contains(OrderCompleted.class)
            .matching(OrderCompleted::getOrderId, reference.getId());
  }
}
```


# Time Events
It’s an event-based approach to time to trigger actions that are tied to a particular period of time having passed.
`spring-modulith-moments` package provides a way to define time events and listeners for them.

> ![Document](https://docs.spring.io/spring-modulith/reference/moments.html)






