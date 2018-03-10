export default function (store) {
	return class CounterComponent extends window.HTMLElement {
		constructor() {
			super();
			this.store = store;

			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange(state) {
			this.innerHTML = this.render();
		}

		connectedCallback() {
			this.innerHTML = this.render();
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback() {
			this.innerHTML = this.render();
			this.store.unsubscribe(this.onStateChange);
		}

		render() {
			return `<span class="resource header">
            			<h3>Doge Coin</h3>
            			<div id="counterCoin">${this.store.state.counter}</div>
        			</span>
        			<span>
						<game-button></game-button>
        			</span>`
		}
	};
}

