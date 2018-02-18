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


function increaseCount(state) {
    const pubSub = new PubSub();

    state.counterDoge ++;

    pubSub.subscribe(countNum => {
        console.log(countNum);
        console.log(countNum.counterDoge);
        console.log(typeof(countNum));
        window.document.getElementById("counterDoge").innerHTML = state.counterDoge;
    });

    pubSub.publish(state);
    window.document.getElementById("counterDoge").innerHTML = state.counterDoge;
 }

