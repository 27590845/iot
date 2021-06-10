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