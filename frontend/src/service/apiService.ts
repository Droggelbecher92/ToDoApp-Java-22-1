export const postNewToDo = (task: string, descript: string) => {
    return fetch(`http://localhost:8080/api/todo`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({'task':task, 'description':descript})
    })
        .then(response => response.json())
        .catch(e => console.log(e.message))
}