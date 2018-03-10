import { Generator } from '../models/generator';

export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor() {
			super();
			this.store = store;
			this.render();
			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange () {
			this.render();
		}

		connectedCallback () {
			this.id = this.dataset.id;
			this.render();
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			this.render();
			this.store.unsubscribe(this.onStateChange);
		}

		render () {
			this.innerHTML = `<ul class="resource card">
            <span class="resource btn">
                <div class="resource header">${this.store.state.generators[this.dataset.id].name}</div>
                <span class="resource header">
                    <div id="counterDoge">${this.store.state.generators[this.dataset.id].quantity}</div>
                </span>
            </span>
            <p class="description">${this.store.state.generators[this.dataset.id].description}</p>
            <p>${this.store.state.generators[this.dataset.id].rate}</p>
            <span class="resource btn">
                ${this.store.state.generators[this.dataset.id].baseCost}
                <button>Buy ${this.store.state.generators[this.dataset.id].name}</button>
            </span>
		</ul>`;

		this.querySelector('button').addEventListener('click', () => {
			this.store.dispatch({
				type:'BUY_GENERATOR',
				payload: {
					name: this.store.state.generators[this.dataset.id].name,
					count: this.store.state.generators[this.dataset.id].quantity
				}
			});
		});
		}
	};
}