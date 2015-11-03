/**
 * Created by umitvardar on 15.08.15.
 */
(function () {
    var app = angular.module("addressbook", []);

    var contacts;
    var contact;

    app.controller("AddressBookController", ['$http', '$scope', '$log', function ($http, $scope, $log) {
        var allReq = {
            method: 'GET',
            url: '/api/all',
            headers: {
                'Content-Type': 'application/json'
            }
        };

        function findAllContacts($scope) {
            $http(allReq).
                then(function (response) {
                    // this callback will be called asynchronously
                    // when the response is available
                    $log.info(response.status);
                    $log.info(response.data);
                    this.contacts = response.data;
                    $scope.contacts = this.contacts;
                }, function (response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status
                    $log.info(response.status);
                });
            this.contact = {};
        };

        findAllContacts($scope);


        this.selectContact = function (contact) {
            this.contact = contact;
        };

        this.addContact = function () {

            var saveReq;
            saveReq = {
                method: 'POST',
                url: '/api/save',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: this.contact
            };

            $http(saveReq).
                then(function (response) {
                    // this callback will be called asynchronously
                    // when the response is available
                    $log.info(response.status);
                    //this.contacts.push(this.contact);
                    $scope.contacts = this.contacts;
                    findAllContacts($scope);

                }, function (response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status
                    $log.info(response.status);
                });
            this.contact = {};
        };

        this.resetContact = function(){
            this.contact={};
        }


        this.deleteContact = function (contact) {

            var deleteReq;
            deleteReq = {
                method: 'DELETE',
                url: '/api/delete/' + contact.id,
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $log.info('Deleting contact with id ' + contact.id);
            $http(deleteReq).
                then(function (response) {
                    // this callback will be called asynchronously
                    // when the response is available
                    $log.info(response.status);
                    var index = this.contacts.indexOf(contact);
                    this.contacts.splice(index, 1);
                    $scope.contacts = this.contacts;
                    this.contact = {};
                }, function (response) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status
                    $log.info(response.status);
                });
        };

    }]);
})();