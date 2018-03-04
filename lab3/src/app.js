import '@webcomponents/webcomponentsjs';

import { loop } from './game';
import Store from './store';
import reducer from './reducer';

import ButtonComponent from './views/button';
import CounterComponent from './views/counter';
import ExampleComponent from './views/example';
import GeneratorComponent from './views/generator';
import StoryBookComponent from './views/story-book';

main();

// main function wraps everything at top level
function main() {
	// TODO: fill the blank based on the theme you have choosen
	const initialState = {
		example: 'Doge Coin',
		counter: 0,
		generators: [
			{
				name = 'Doge Click',
				description = 'Doge Coin, is a rare comodity and is used to pay our lord Doge God.',
				rate = 5,
				quantity = 0,
				baseCost = 25,
				unlockValue = 10
			},
			{
				name = 'Bork Power',
				description = 'Bork Bork Bork Bork Bork Bork *laughs in Dog*',
				rate = 10,
				quantity = 0,
				baseCost = 25,
				unlockValue = 20
			},
			{
				name = 'Woofer',
				description = 'Woof so strong the coins just flow out!',
				rate = 25,
				quantity = 0,
				baseCost = 100,
				unlockValue = 60
			}
		],
		story: []
	};

	// initialize store
	const store = new Store(reducer, initialState);
	console.log(ExampleComponent(store));

	// define web components
	window.customElements.define('component-example', ExampleComponent(store));
	// no longer used
	window.customElements.define('game-button', ButtonComponent(store));
	window.customElements.define('game-counter', CounterComponent(store));
	// lab 3
	window.customElements.define('game-generator', GeneratorComponent(store));
	// homework 1
	window.customElements.define('game-story-book', StoryBookComponent(store));

	// For ease of debugging purpose, we will expose the critical store under window
	// ps: window is global
	window.store = store;

	// start game loop
	loop(store);
}
