export default function reducer(state, action) {
	switch (action.type) {
		case 'BUY_GENERATOR':
			if (state.generators.name == actiono.payload.name) {
				state.counter = state.counter - state.generators.baseCost;
				state.generators.quantity = state.generators.quantity + action.payload.quantity;

				return state;
			}
		default:
			return state;
	}
}

