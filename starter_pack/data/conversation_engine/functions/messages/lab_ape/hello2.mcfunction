# run as the player

# message id: 3

# reset the sucsess scoreboard
scoreboard players set bool CE_suc 0

# check if the player came from a valid previous node (to prevent manual use of /trigger)
execute if score @s CE_current_node matches 0 run scoreboard players set bool CE_suc 1

# special case in case the previous node is itself in that case CE_resend of bool is set to 1 (use this to prevent commands that for example give items are executed twice)
execute store success score bool CE_resend if score @s CE_current_node matches 3 run scoreboard players set bool CE_suc 1

    # give the choices
    execute if score bool CE_suc matches 1 run tellraw @s [{"text":"\n\n\n[lab ape]","color":"#5e4500"},{"text":"ooo oo aaa i'm an ape","color":"white"}]



    # update the last run node
    execute if score bool CE_suc matches 1 run scoreboard players operation @s CE_current_node = @s CE_trigger

