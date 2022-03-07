import React from 'react';
import {getByTestId, render, screen} from '@testing-library/react';
import ToDoCard from "./ToDoCard";

test('renders todocard', () => {
    const {getByTestId} = render(<ToDoCard infos={{
        id : "string",
        task: "string",
        description : "string",
        status:  "OPEN"}} update={() => console.log('hallo')} />);
    expect(getByTestId('the-task').textContent).toEqual('string');
    expect(getByTestId('the-desc').textContent).toEqual('string');
    expect(getByTestId('btn-back').textContent).toEqual('<Löschen');
    expect(getByTestId('btn-next').textContent).toEqual('Weiter>');
});