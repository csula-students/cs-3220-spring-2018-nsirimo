export default function reducer(state, action) {
	switch (action.type) {
		case 'BUY_GENERATOR':
			state.generators.forEach((generator) => {
				if (generator.name == action.payload.name) {
					state.counter = state.counter - (generator.baseCost + 1);
					generator.quantity++;
					return generator;
				}
			});
		case 'BUTTON_CLICK':
			state.counter++;
			return state;
		case 'INCREMENT':
			state.counter = state.counter + (action.payload.rate * action.payload.count);
			return state;
		case 'CHECK_STORY':
			state.storys.forEach((story) => {
				if (story.name == action.payload.name) {
					story.state = 'visible';
				}
			});
			return state;
		default:
			return state;
	}
}

