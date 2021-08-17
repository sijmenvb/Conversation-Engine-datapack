# detect when a player richt clicks a villager
scoreboard objectives add CE_talking minecraft.custom:minecraft.talked_to_villager
# scoreboard for storing sucsesses of functions to do conditionals
scoreboard objectives add CE_suc dummy
scoreboard objectives add CE_suc2 dummy
scoreboard objectives add CE_resend dummy
scoreboard objectives add CE_buy_count dummy
# scoreboard for limiting recursion
scoreboard objectives add CE_rec dummy

# trigger scoreboard to be acsessed by players without permissions.
scoreboard objectives add CE_trigger trigger
# a way to store the current node in the dialouge tree. used to prevent players from jumping unexpectedly by using /trigger manually.
scoreboard objectives add CE_current_node dummy

# groups for optimazation to be used by fake player CE_mannager
scoreboard objectives add CE_group_00 dummy

# scoreboard for villagers to be used by fake player CE_mannager
scoreboard objectives add lab_rat dummy
scoreboard objectives add lab_ape dummy

# scoreboard for detecting if a player joins the game
execute unless entity @a run scoreboard objectives remove CE_leave_game
# not fully shure what the one above does but it got reccomended to me and it can't hurt.
scoreboard objectives add CE_leave_game custom:leave_game

# reset the scores for all players:
execute as @a run function conversation_engine:player_log_on

say conversation engine initialized