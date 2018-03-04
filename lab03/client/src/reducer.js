export default function reducer(state, action) {
	switch (action.type) {
		case 'BUY_GENERATOR':
		state.generators.forEach((generator) => {
			if (generator.name == action.payload.name) {
				state.counter = state.counter - generator.baseCost;
				generator.quantity++;
				return generator;
			}
		});
		case constants.actions.BUTTON_CLICK:
			return state;
		default:
			return state;
	}
}

