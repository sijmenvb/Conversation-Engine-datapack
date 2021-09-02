# run by server

# each tick check if a player cicked a villager.
execute as @a[scores={CE_talking=1..}] run function conversation_engine:talking
# check if a conversation is going on
function conversation_engine:group/group
# you can always use the trigger
scoreboard players enable @a CE_trigger

# check if a player just logged back on.
execute as @a[scores={CE_leave_game = 1..}] run function conversation_engine:player_log_on

# make sure all NPC's face players when less than 7 blocks away.
execute at @a as @e[type=minecraft:villager,tag=CE_npc,distance=..7] at @s facing entity @a[limit=1,sort=nearest,distance=..7] feet run tp @s ~ ~ ~ ~ ~