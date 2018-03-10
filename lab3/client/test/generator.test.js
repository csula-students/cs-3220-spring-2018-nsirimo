/*eslint-env node, jest */
import Generator from '../src/models/generator';
import * as mock from './mock';

const mockGenerator = mock.generator;

describe('generator models', () => {
	test('getCost should return base cost when no quantity', () => {
		const generator = new Generator(Object.assign({}, mockGenerator));
		const expected = 25;
		expect(generator.getCost()).toEqual(expected);
	});

	test('getCost should return base cost when no quantity', () => {
		const generator = new Generator(Object.assign({}, mockGenerator, {quantity: 2}));
		const expected = 27.56;
		expect(generator.getCost()).toEqual(expected);
	});

	test('generate should return zero values when no quantity', () => {
		const generator = new Generator(Object.assign({}, mockGenerator));
		const expected = 0;
		expect(generator.generate()).toEqual(expected);
	});


	test('generate should return rate * quantiy value when there is quantity', () => {
		const generator = new Generator(Object.assign({}, mockGenerator, {quantity: 3}));
		const expected = 15;
		expect(generator.generate()).toEqual(expected);
	});
});