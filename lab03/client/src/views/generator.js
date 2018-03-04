import { Generator } from "../models/generator";

export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor() {
			super();
			this.store = store;

			// TODO: render generator initial view
			this.onStateChange = this.handleStateChange.bind(this);
			// TODO: subscribe to store on change event
			// TODO: add click event
			this.addEventListener('click', () => {
				this.store.dispatch({
					type:'BUY_GENERATOR',
					payload: {
						name: this.store.state.generators[this.dataset.id].name,
						count: this.store.state.generators[this.dataset.id].quantity
					}
				})
			})
		}

		handleStateChange (state) {
			console.log("Purchased a gen!");
		}

		connectedCallback() {
			console.log("genOnCallback");
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback() {
			console.log("genonDisCalled");
			this.store.unsubscribe(this.onStateChange);
		}
	};
}