// PubSub is single object for publish data to multiple subscribers
class PubSub {
    constructor () {
        this.subscribers = [];
    }

    // subscribe allows a new subscriber to listen for changes by providing
    // callback function in the parameter
    subscribe (fn) {
        this.subscribers.push(fn);
    }

    // one can publish any data to subscribers
    publish (data) {
        this.subscribers.forEach(subscriber => {
            subscriber(data);
        });
    }
}

const pubSub = new PubSub();

function increaseDoge(state) {
    state.counterDoge ++;

    pubSub.subscribe(countNum => {
        window.document.getElementById("counterDoge").innerHTML = state.counterDoge;
    });

    pubSub.publish(state);
    window.document.getElementById("counterDoge").innerHTML = state.counterDoge;
 }

 function increaseWoof(state) {
    state.counterWoof ++;

    pubSub.subscribe(countNum => {
        window.document.getElementById("counterWoof").innerHTML = state.counterWoof;
    });

    pubSub.publish(state);
    window.document.getElementById("counterWoof").innerHTML = state.counterWoof;
 }

 function increaseBork(state) {
    state.counterBork ++;

    pubSub.subscribe(countNum => {
        window.document.getElementById("counterBork").innerHTML = state.counterBork;
    });

    pubSub.publish(state);
    window.document.getElementById("counterBork").innerHTML = state.counterBork;
 }
