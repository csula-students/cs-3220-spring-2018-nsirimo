import Story from '../src/models/story';
import constants from './constants';

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
			state.counter = action.payload;
			return state;
		case 'CHECK_STORY':
			state.story.forEach((storys) => {
				let storyTemp = new Story(storys);
				if (storyTemp.isUnlockYet(state.counter)) {
					storys.state = "visible";
				}
			});
		default:
			return state;
	}
}

