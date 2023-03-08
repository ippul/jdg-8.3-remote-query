# jdg-8.3-remote-query

### Configure Red Hat Data grid

- Download Red Hat Data grid 8.4 from customer portal

- Create a new user using the following command
```shell
$DATAGRID_HOME/bin/cli.sh user create admin -p "password" -g admin,Admin
```

- Update Data grid configuration $DATAGRID_HOME/server/conf/infinispan.xml

```xml
<infinispan
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="urn:infinispan:config:13.0 https://infinispan.org/schemas/infinispan-config-13.0.xsd
                            urn:infinispan:server:13.0 https://infinispan.org/schemas/infinispan-server-13.0.xsd"
        xmlns="urn:infinispan:config:13.0"
        xmlns:server="urn:infinispan:server:13.0">

    <cache-container name="default" statistics="true">
        <transport cluster="${infinispan.cluster.name:cluster}" stack="${infinispan.cluster.stack:tcp}" node-name="${infinispan.node.name:}"/>
        <security>
            <authorization/>
        </security>
        <replicated-cache mode="SYNC" name="REPL_TEST_CACHE" />
    </cache-container>

    <server xmlns="urn:infinispan:server:13.0">
        <interfaces>
            <interface name="public">
                <inet-address value="${infinispan.bind.address:127.0.0.1}"/>
            </interface>
        </interfaces>

        <socket-bindings default-interface="public" port-offset="${infinispan.socket.binding.port-offset:0}">
            <socket-binding name="default" port="${infinispan.bind.port:11222}"/>
            <socket-binding name="memcached" port="11221"/>
        </socket-bindings>

        <security>
            <credential-stores>
                <credential-store name="credentials" path="credentials.pfx">
                    <clear-text-credential clear-text="secret"/>
                </credential-store>
            </credential-stores>
            <security-realms>
                <security-realm name="default">
                    <properties-realm groups-attribute="Roles">
                        <user-properties path="users.properties"/>
                        <group-properties path="groups.properties"/>
                    </properties-realm>
                </security-realm>
            </security-realms>
        </security>

        <endpoints socket-binding="default" security-realm="default" />
    </server>
</infinispan>

```

### Run the test
```shell
mvn clean install
mvn exec:exec
```