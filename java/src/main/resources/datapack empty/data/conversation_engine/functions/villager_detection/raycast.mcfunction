# always run by the player

# add one to the recusion limmit
scoreboard players add rec CE_rec 1
# reset the sucsess scoreboard
scoreboard players set bool CE_suc 0

# check if there is a npc villager at this position if so make CE_suc 1 and make the villager run the talk function.
execute store success score bool CE_suc as @e[type=villager,tag=CE_npc,distance=..1.5] run function conversation_engine:messages/talk
# if the villager was not found and we have not exeeded the recursion limit call this function one block forward.
execute if score bool CE_suc matches 0 unless score rec CE_rec matches 7 positioned ^ ^ ^1 run function conversation_engine:villager_detection/raycast
# if we have not found a NPC villager stop talking. 
execute if score rec CE_rec matches 7 unless score bool CE_suc matches 1 run scoreboard players set @s CE_talking 0

particle crit ~ ~ ~ 0 0 0 0 7 normal 