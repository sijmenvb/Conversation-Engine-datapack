# run by server

# this is for grouping of villagers so we don't have to check each villager each tick only each group.

# check if there is a conversation in a group:
execute if score CE_mannager CE_group_00 matches 1 run function conversation_engine:group/000