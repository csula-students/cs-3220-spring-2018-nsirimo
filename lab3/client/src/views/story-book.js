export default function (store) {
	return class StoryBookComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			this.render();
			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			this.render();
		}

		addText(currentStory, addText) {
			//console.log(text);
			currentStory = currentStory + ('\n' + addText);
			return currentStory;
		}

		connectedCallback () {
			this.render();
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			this.render();
			this.store.unsubscribe(this.onStateChange);
		}

		render () {
			let boxMessage = 'The Story Begins...';
			this.store.state.storys.forEach((story) => {
				if(story.state == 'visible'){
					boxMessage = this.addText(boxMessage, story.description);
				}
			});
			this.innerHTML = `<div>
			<textarea class="scrollabletextbox" readonly="true">${boxMessage}</textarea>
		</div>`;
		}
	};
}

