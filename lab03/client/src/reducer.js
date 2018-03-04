export default function reducer(state, action) {
	switch (action.type) {
		case 'BUY_GENERATOR':
			console.log("action ", action);
			console.log("action.payload: ", action.payload);
			// if (state.generators.name == action.payload.name) {
			// 	state.counter = state.counter - state.generators.baseCost;
			// 	state.generators.quantity = state.generators.quantity + action.payload.quantity;

				return state;
			//}

		case constants.actions.BUTTON_CLICK:
			return state;
		default:
			return state;
	}
}

