export default function (store) {
	return class ButtonComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			this.innerHTML = this.render();
			// TODO: add click event to increment counter
			// hint: use "store.dispatch" method (see example component)
			this.addEventListener('click', () => {
				this.store.dispatch({type: 'BUTTON_CLICK'});
			});
		}

		render () {
			return `
			<button class="collectionHeader button">Collect Coins</button>
			`
		}
	};

}
