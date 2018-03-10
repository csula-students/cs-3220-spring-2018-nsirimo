import reducer from '../src/reducer';
import constants from '../src/constants';
import * as mock from './mock';


test('should be able to muate resource and generators on "BUY_GENERATOR" action', () => {
	const action = {
		type: constants.actions.BUY_GENERATOR,
		payload: {
			name: 'Doge Click',
			quantity: 1
		}
	};
	const initialState = {
		counter: 25,
		generators: [ mock.generator ]
	};
	const expected = {
		counter: 0,
		generators: [ Object.assign({}, mock.generator, {quantity: 1}) ]
	};
	expect(reducer(initialState, action)).toEqual(expected);
});