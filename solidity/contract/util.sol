pragma solidity ^0.4.24;
import "./Table.sol";

contract util {
    constructor() public {
        
    }
    
    function query(string table_name, string primary_key) returns(int, Entry){
        TableFactory tf = TableFactory(0x1001);
        Table table = tf.openTable(table_name);
        Entries entries = table.select(primary_key, table.newCondition());
        int result = 0;
        if (0 == uint256(entries.size())) {
            return (-1, table.newEntry());
        } else {
            return (0, entries.get(0));
        }
    }
}
