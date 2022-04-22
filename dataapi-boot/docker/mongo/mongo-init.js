


db.createUser({ user:'admin',pwd:'123456',roles:[ { role:'userAdminAnyDatabase', db: 'admin'},"readWriteAnyDatabase"]});

db.createUser(
    {
        user: "iot",
        pwd: "iot",
        roles: [
            {
                role: "dbOwner",
                db: "iotdata"
            }
        ]
    }
);