# zalora_assignment
This project is writed by koltin.

WorkFlow:

 -Call API from network to get data page by page then save it to local, next time will load from local if data exist otherwise will get from API.
 
 -Every 15 minutes app will run an background service to update database (the phone needs to turn on the internet and in idle status).

Project is using MVVM+Clean architect.

There are 3 layers in this project:

- Doman: Repository(interface), model, use case.

- Data: DataSource(Remote/Local), API(Service), Repository(Implement), Model Remote, Mapper(From Remote to Domain).

- Presentation: Views, ViewModel, DI, Service/WorkManager(Update data every 15 minutes if internet is turning on).

Dependencies between 3 layers:

<img src="https://user-images.githubusercontent.com/71365481/93423718-cfbf3980-f8e0-11ea-9ea3-81c1ac845ebb.png">
